defmodule RadioMonWeb.Router do
  use Phoenix.Router

  pipeline :browser do
    plug CORSPlug
    plug :accepts, ["html"]
  end

  pipeline :api do
    plug CORSPlug
    plug :accepts, ["json"]
  end

  pipeline :file do
    plug CORSPlug
  end

  scope "/" do
    pipe_through :browser
    get "/", RadioMonWeb.PlayerController, :show
  end

  scope "/" do
    pipe_through :api
    post "/current_audio", RadioMonWeb.PlayerController, :current_audio
  end

  scope "/" do
    pipe_through :file
    get "/media/:file_name", RadioMonWeb.PlayerController, :get_media
  end
end