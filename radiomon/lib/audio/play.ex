defmodule RadioMon.Audio.Play do
  use Ecto.Schema

  import Ecto.Changeset
  import Ecto.Query, only: [from: 2]

  schema "audio_plays" do
    field :started, :utc_datetime
    belongs_to :audio_item, RadioMon.Audio.Item
    field :remaining, :integer, virtual: true
  end

  @doc"""
  Make change set by casting params onto struct and putting audio item association.
  """
  def changeset(struct, params, audio_item) do
    struct
    |> cast(params, [:started])
    |> put_assoc(:audio_item, audio_item)
  end

  @doc """
  Query to get the most recently started audio play
  """
  def last do
    from p in __MODULE__,
      preload: [:audio_item],
      order_by: [desc: p.started],
      limit: 1
  end

  @doc """
  Calculate the remaining field of the struct.
  """
  def remaining(struct) do
    now = DateTime.utc_now()
    elapsed = DateTime.diff(now, struct.started, :millisecond)
    remaining = struct.audio_item.duration - elapsed
    Map.put(struct, :remaining, remaining)
  end

end