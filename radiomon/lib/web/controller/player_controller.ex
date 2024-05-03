defmodule RadioMonWeb.PlayerController do
  use RadioMonWeb, :controller

  def show(conn, _params) do
    render(conn, :show)
  end
end