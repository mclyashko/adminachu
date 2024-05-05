defmodule RadioMonWeb.PlayerController do
  use RadioMonWeb, :controller

  alias RadioMon.Processor.Audio

  def show(conn, _params) do
    render(conn, :show)
  end

  def current_audio(conn, _param) do
    broadcast = RadioMon.Processor.current_audio_play()
    render(conn, :current_audio, broadcast: broadcast)
  end

  def get_media(conn, %{"file_name" => file_name_with_suffix}) do
    file_name = String.replace(file_name_with_suffix, ".mp3", "")
    conn
    |> put_resp_content_type("audio/mpeg")
    |> send_resp(200, Audio.get_audio_data(file_name).file)
  end
end