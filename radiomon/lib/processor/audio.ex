defmodule RadioMon.Processor.Audio do
  use Ecto.Schema

  import Ecto.Changeset
  import Ecto.Query, only: [from: 2]

  @primary_key {:id, :binary_id, autogenerate: true}
  schema "audio" do
    field :name, :string
    field :description, :string
    field :file, :binary
    field :duration, :integer, virtual: true
  end

  @doc """
  Make a change set by casting params onto struct.
  """
  def changeset(struct, params) do
    cast(struct, params, [:duration])
  end

  @doc """
  Query to get the last audio
  """
  def last do
    from a in __MODULE__,
      order_by: [desc: a.id],
      limit: 1
  end

  @doc """
  Calculate the duration field of the struct.
  """
  def duration(struct) do
    tag = ID3v2.frames(struct.file)
    %{"TLEN" => tlen} = tag
    tlen_integer = String.to_integer(tlen)
    Map.put(struct, :duration, tlen_integer)
  end
end