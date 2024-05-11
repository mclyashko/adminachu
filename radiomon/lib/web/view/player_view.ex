defmodule RadioMonWeb.PlayerView do
  use RadioMonWeb, :view

  alias RadioMon.Processor.Media

  def render("current_audio.json", %{broadcast: broadcast} = _assigns) do
    %{
      file_url: Media.file_url(broadcast),
      remaining: broadcast.remaining,
      name: broadcast.audio.name,
      description: broadcast.audio.description
    }
  end
end