import React, { useState, useEffect, useRef } from "react";
import { Col, Row } from "antd";
import { EllipsisOutlined } from "@ant-design/icons";
import { track, addSong, addArtist, album, addAlbum } from "../../client"; //need all users playlists

const Playlists = ({ id }) => {
  const [song, setSong] = useState({});
  const [theAlbum, setTheAlbum] = useState({});
  const isFirstRender = useRef(true);

  const newSong = {
    song_id: 0,
    spotify_id: "",
    title: "",
    albumId: "",
    releaseDate: "",
    artist_list: [],
  };

  const newArtist = {
    artist_id: 0,
    spotify_id: "",
    name: "",
    album_list: [],
  };

  const newAlbum = {
    album_id: 0,
    spotify_id: "",
    name: "",
    songs: 1,
    albumImage: "",
    artist: [],
    songs_list: [],
  };

  const fetchSong = async () => {
    const res = await track(id);
    const data = await res.json();

    return data;
  };

  const fetchAlbum = async () => {
    const res = await album("4uIhRJj1Au4TiyHhCOZys5");
    const data = await res.json();

    return data;
  };

  useEffect(() => {
    const getSong = async () => {
      const song = await fetchSong();
      setSong(song);
    };

    getSong();
  }, []);

  useEffect(() => {
    if (isFirstRender.current === true) {
      isFirstRender.current = false;
    } else {
      const getAlbum = async () => {
        const album = await fetchAlbum();
        setTheAlbum(album);
        console.log(album);
      };

      getAlbum();
    }
  }, [song]);

  const addToDatabase = () => {
    newSong.spotify_id = song.spotify_id;
    console.log(song.spotify_id);
    newSong.title = song.title;
    newSong.albumId = song.albumId;
    newSong.releaseDate = song.releaseDate;
    newSong.artist_list = [];
    addSong(song);

    for (let artistArribute in song.artist_list) {
      newArtist.name = song.artist_list[artistArribute].name;
    }

    for (let artistArribute in song.artist_list) {
      newArtist.spotify_id = song.artist_list[artistArribute].spotify_id;
    }

    newAlbum.album_id = 0;
    newAlbum.spotify_id = theAlbum.spotify_id;
    newAlbum.name = theAlbum.name;
    newAlbum.songs = theAlbum.songs;
    console.log(theAlbum.songs);
    newAlbum.albumImage = theAlbum.albumImage;
    // console.log(theAlbum.artist[0].artist_id);

    console.log(newAlbum);
    console.log(newSong);

    addAlbum(theAlbum);
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
