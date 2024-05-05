defmodule RadioMon.Processor.Audio do
  use Ecto.Schema

  import Ecto.Changeset
  import Ecto.Query, only: [from: 2]

  alias RadioMon.Repo

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
  Query to get audio by name
  """
  def by_name(name) do
    from a in __MODULE__,
      where: a.name == ^name
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

  def get_audio_data(file_name) do
    by_name(file_name)
      |> Repo.one()
  end
end