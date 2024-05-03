defmodule RadioMon.Processor do
  alias RadioMon.Repo
  alias RadioMon.Processor.Broadcast
  alias RadioMon.Processor.Audio

  @doc"""
  Get the most recent audio play, making a new one if the most recent
  doesn't have enough time remaining.
  """
  def current_audio_play(opts) do
    threshold = Keyword.fetch!(opts, :threshold)
    last_play =
      Broadcast.last()
      |> Repo.one()
      |> Broadcast.remaining()
    case last_play do
      nil -> next_audio_play()
      last_play when last_play.remaining < threshold -> next_audio_play()
      last_play -> last_play
    end
  end

  def next_audio_play do
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
    |> Broadcast.changeset(%{streamed_at: DateTime.utc_now()}, audio_item)
    |> Repo.insert!()
  end
end
