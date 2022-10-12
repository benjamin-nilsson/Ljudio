import React, { useState, useEffect, useRef } from "react";
import { Col, Row } from "antd";
import axios from "axios";
import { EllipsisOutlined } from "@ant-design/icons";
import AuthService from "../../services/auth.service";
import {
  track,
  addSong,
  album,
  addAlbum,
  addSongToPlaylist,
  getUserPlaylists,
  getUser,
} from "../../client"; //need all users playlists
import EmployeeService from "../../services/EmployeeService";

const Playlists = ({ id }) => {
  const [song, setSong] = useState({});
  const [theAlbum, setTheAlbum] = useState({});
  const isFirstRender = useRef(true);
  const currentUser = AuthService.getCurrentUser();
  const [playlists, setPlaylists] = useState(null);
  const service = EmployeeService;

  const fetchSong = async () => {
    const res = await track(id);
    const data = await res.json();

    return data;
  };

  const fetchAlbum = async () => {
    console.log(song.albumId);
    const res = await album(song.albumId);
    const data = await res.json();

    return data;
  };

  useEffect(() => {
    service
      .getUserPlaylist(currentUser.id)
      .then((resp) => setPlaylists(resp.data));
  }, []);

  useEffect(() => {
    const getSong = async () => {
      const song = await fetchSong();
      console.log(song);
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
  }, [song, setSong]);

  const addToDatabase = (playlistId) => {
    addSong(song);
    addSongToPlaylist(playlistId, id);
    addAlbum(theAlbum);
  };

  return (
    <div style={{ margin: "1rem 0" }}>
      {console.log(currentUser.id)}
      {console.log(playlists)}
      {playlists != null &&
        playlists.map((result, index) => (
          <div
            key={index}
            onClick={() => {
              addToDatabase(result.id);
            }}
            style={{
              cursor: "pointer",
            }}
          >
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
                    style={{
                      color: "#fff",
                      marginBottom: "0",
                      fontWeight: "bold",
                    }}
                  >
                    {result.name}
                  </h3>
                  <p style={{ color: "#fff", marginBottom: "0" }}>{""}</p>
                </div>
              </Col>
              <Col span={2}></Col>
            </Row>
          </div>
        ))}
    </div>
  );
};

export default Playlists;
