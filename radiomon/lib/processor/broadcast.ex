defmodule RadioMon.Processor.Broadcast do
  use Ecto.Schema

  import Ecto.Changeset
  import Ecto.Query, only: [from: 2]

  @primary_key {:id, :binary_id, autogenerate: true}
  @foreign_key_type :binary_id
  schema "broadcast" do
    field :streamed_at, :utc_datetime
    belongs_to :audio, RadioMon.Processor.Audio
    field :remaining, :integer, virtual: true
  end

  @doc"""
  Make change set by casting params onto struct and putting audio item association.
  """
  def changeset(struct, params, audio_item) do
    struct
    |> cast(params, [:streamed_at])
    |> put_assoc(:audio, audio_item)
  end

  @doc """
  Query to get the most recently started broadcast
  """
  def last do
    from b in __MODULE__,
      preload: [:audio],
      order_by: [desc: b.streamed_at],
      limit: 1
  end

  @doc """
  Calculate the remaining field of the struct.
  """
  def remaining(struct) when struct == nil do
    nil
  end

  def remaining(struct) do
    calculated_audio =
      struct.audio
      |> RadioMon.Processor.Audio.duration()
    now = DateTime.utc_now()
    elapsed = DateTime.diff(now, struct.streamed_at, :millisecond)
    remaining = calculated_audio.duration - elapsed
    Map.put(struct, :remaining, remaining)
  end

  def ended(struct) when struct == nil do
    DateTime.utc_now()
  end

  def ended(struct) do
    calculated_audio =
      struct.audio
      |> RadioMon.Processor.Audio.duration()
    DateTime.add(struct.streamed_at, calculated_audio.duration, :millisecond)
  end
end