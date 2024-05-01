defmodule RadioMon.Audio.Item do
  use Ecto.Schema

  import Ecto.Changeset

  schema "audio_items" do
    field :duration, :integer
  end

  @doc """
  Make a change set by casting params onto struct.
  """
  def changeset(struct, params) do
    cast(struct, params, [:duration])
  end
end