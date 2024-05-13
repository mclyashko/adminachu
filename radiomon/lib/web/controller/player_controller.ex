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
    audio = case Cachex.exists?(:audio_cache, file_name) do
      {:ok, true} -> {:ok, cached_audio} = Cachex.get(:audio_cache, file_name)
        cached_audio
      _ -> to_cache = Audio.get_audio_data(file_name)
        Cachex.put(:audio_cache, file_name, to_cache)
        to_cache
      end
    conn
    |> put_resp_content_type("audio/mpeg")
    |> send_resp(200, audio)
  end
end