defmodule RadioMon.Migrations.AudioPlays do
  use Ecto.Migration

  # change to broadcast

  def change do
    create table("audio_plays") do
      add :started, :utc_datetime
    end
  end
end