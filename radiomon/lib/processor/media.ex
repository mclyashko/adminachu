defmodule RadioMon.Processor.Media do
  def file_url(struct) do
    env = fetch_env()
    base_url = Keyword.fetch!(env, :base_url)
    base_url <> struct.audio.name <> ".mp3"
  end

  def fetch_env() do
    case Application.fetch_env(:radio_mon, __MODULE__) do
      :error -> []
      {:ok, opts} -> opts
    end
  end
end