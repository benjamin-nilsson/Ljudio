import React, { useState, useEffect } from "react";
import { Col, Row } from "antd";
import { EllipsisOutlined } from "@ant-design/icons";
import { track, album, addSong, addArtist } from "../../client"; //need all users playlists

const Playlists = ({ id }) => {
  const [song, setSong] = useState({});

  const theSong = {
    song_id: 0,
    spotify_id: "",
    title: "",
    albumId: "",
    releaseDate: "",
    artist_list: [],
  };

  const artist = {
    artist_id: 0,
    spotify_id: "",
    name: "",
    album_list: [],
  };

  const fetchSong = async () => {
    const res = await track(id);
    const data = await res.json();

    return data;
  };

  useEffect(() => {
    const fetchAndAddSong = async () => {
      const song = await fetchSong();
      setSong(song);
    };

    fetchAndAddSong();
  }, []);

  const addToDatabase = () => {
    theSong.spotify_id = song.spotify_id;
    console.log(song.spotify_id);
    theSong.title = song.title;
    theSong.albumId = song.albumId;
    theSong.releaseDate = song.releaseDate;
    theSong.artist_list = [];
    addSong(theSong);

    for (var artistArribute in song.artist_list) {
      artist.name = song.artist_list[artistArribute].name;
    }

    for (var artistArribute in song.artist_list) {
      artist.spotify_id = song.artist_list[artistArribute].spotify_id;
    }

    console.log(song.artist_list);
    addArtist(artist);
  };

  return (
    <div style={{ margin: "1rem 0" }}>
      <Row
        style={{
          paddingTop: "1rem",
          display: "flex",
          flexDirection: "row",
          alignItems: "center",
        }}
      >
        <Col span={5}>
          <img src={""} alt="" />
        </Col>
        <Col span={17}>
          <div>
            <h3
              style={{ color: "#fff", marginBottom: "0", fontWeight: "bold" }}
            >
              {""}
            </h3>
            <p style={{ color: "#fff", marginBottom: "0" }}>{""}</p>
          </div>
        </Col>
        <Col span={2}>
          <EllipsisOutlined onClick={() => addToDatabase()} />
        </Col>
      </Row>
    </div>
  );
};

export default Playlists;
