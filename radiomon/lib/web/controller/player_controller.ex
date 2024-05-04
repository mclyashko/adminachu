defmodule RadioMonWeb.PlayerController do
  use RadioMonWeb, :controller

  def show(conn, _params) do
    render(conn, :show)
  end

  def current_audio(conn, _param) do
    broadcast = RadioMon.Processor.current_audio_play()
    render(conn, :current_audio, broadcast: broadcast)
  end
end