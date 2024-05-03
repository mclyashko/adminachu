defmodule RadioMon.Processor.Sequence do
  alias RadioMon.Repo

  use Ecto.Schema

  import Ecto.Changeset
  import Ecto.Query, only: [from: 2]

  @primary_key {:id, :binary_id, autogenerate: true}
  @foreign_key_type :binary_id
  schema "sequence" do
    field :order, :integer
    belongs_to :audio, RadioMon.Processor.Audio
    field :new_order, :integer, virtual: true
  end

  @doc """
  Make a change set by casting params onto struct.
  """
  def changeset(struct, params) do
    cast(struct, params, [:order])
  end

  @doc """
  Query to get the first sequence
  """
  def first do
    from s in __MODULE__,
      preload: [:audio],
      order_by: [asc: s.order],
      limit: 1
  end

  @doc """
  Query to get the last sequence
  """
  def last do
    from s in __MODULE__,
      order_by: [desc: s.order],
      limit: 1
  end

  @doc """
  Calculate the new order field of the struct.
  """
  def new_order(struct) do
    new_order = Repo.one(last()).order + 1
    Map.put(struct, :new_order, new_order)
  end

end