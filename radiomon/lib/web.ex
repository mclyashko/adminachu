defmodule RadioMonWeb do
  defmacro __using__(:view) do
    quote do
      use Phoenix.View, root: "lib/web/template"
      use Phoenix.HTML

      alias RadioMonWeb.Router.Helpers, as: Routes
    end
  end

  defmacro __using__(:controller) do
    quote do
      use Phoenix.Controller

      import Plug.Conn

      alias RadioMonWeb.PlayerController.Helpers, as: Routes
    end
  end
end