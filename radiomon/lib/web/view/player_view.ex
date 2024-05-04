defmodule RadioMonWeb.PlayerView do
  use RadioMonWeb, :view

  def render("current_audio.json", %{broadcast: broadcast} = _assigns) do
    %{
#      file_data: broadcast.audio.file,
      remaining: broadcast.remaining,
      name: broadcast.audio.name,
      description: broadcast.audio.description
    }
  end
end