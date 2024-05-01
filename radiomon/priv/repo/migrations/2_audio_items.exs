defmodule RadioMon.Migrations.AudioItems do
  use Ecto.Migration

  # change to audio
  # add duration ??? -> maybe calculate it !!!

  def change do
    create table("audio_items") do
      add :duration, :integer
    end

    alter table("audio_plays") do
      add :audio_item_id, references("audio_items")
    end
  end
end