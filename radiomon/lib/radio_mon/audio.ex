defmodule RadioMon.Audio do
  alias RadioMon.Repo
  alias RadioMon.Audio.Play
  alias RadioMon.Audio.Item

  import IO

  @doc"""
  Get the most recent audio play, making a new one if the most recent
  doesn't have enough time remaining.
  """
  def current_audio_play(opts) do
    threshold = Keyword.fetch!(opts, :threshold)
    last_play =
      Play.last()
      |> Repo.one()
      |> Play.remaining()
    case last_play do
      nil -> next_audio_play()
      last_play when last_play.remaining < threshold -> next_audio_play()
      last_play -> last_play
    end
  end

  def next_audio_play do
    params = %{started: DateTime.utc_now()}
    audio_item = %Item{duration: 10_000}
    %Play{}
    |> Play.changeset(params, audio_item)
    |> Repo.insert!()
  end
end
