defmodule RadioMon.Processor do
  alias RadioMon.Repo
  alias RadioMon.Processor.Broadcast
  alias RadioMon.Processor.Audio

  @doc"""
  Get the most recent audio play, making a new one if the most recent
  doesn't have enough time remaining.
  """
  def current_audio_play(opts \\ []) do
    opts = opts ++ fetch_env()
    threshold = Keyword.fetch!(opts, :threshold)
    last_play =
      Broadcast.last()
      |> Repo.one()
      |> Broadcast.remaining()
    last_play_audio = last_play.audio
      |> Audio.duration()
    if is_nil(last_play) or last_play.remaining < threshold do
      last_ended = Broadcast.ended(last_play)
      next_audio_play(last_ended)
    else
      last_play
      |> Map.put(:audio, last_play_audio)
    end
  end

  def fetch_env() do
    case Application.fetch_env(:radio_mon, __MODULE__) do
      :error -> []
      {:ok, opts} -> opts
    end
  end

  def next_audio_play(last_ended) do
    env = fetch_env()
    gap = Keyword.fetch!(env, :gap)
    sequence_item =
      RadioMon.Processor.Sequence.first()
      |> Repo.one()
      |> RadioMon.Processor.Sequence.new_order()
    sequence_item
      |> RadioMon.Processor.Sequence.changeset(%{order: sequence_item.new_order})
      |> Repo.update!()
    audio_item = sequence_item.audio
      |> Audio.duration()
    %Broadcast{}
    |> Broadcast.changeset(%{streamed_at: DateTime.add(last_ended, gap, :millisecond)}, audio_item)
    |> Repo.insert!()
    |> Broadcast.remaining()
  end
end
