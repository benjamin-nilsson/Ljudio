import { Row, Col } from "antd";
import React, { useEffect, useState, useRef } from "react";
import { getPlaylist } from "./../client";
import { MoreOutlined } from "@ant-design/icons";
import { PlayCircleFilled } from "@ant-design/icons";
import { useNavigate } from "react-router";

const Playlist = ({ playlistName, playlistId, username, image }) => {
  const [songs, setSongs] = useState(null);
  const [data, setData] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const onClick = () => {};

  const setMembersMovies = () => {
    setLoading(true);
    getPlaylist(1)
      .then((resp) => resp)
      .then((res) => res.json())
      .then((data) => {
        setSongs(data);
        console.log(data);
        console.log("hello");
      });

    setLoading(false);
  };

  useEffect(() => {
    setMembersMovies();
    setData(false);
  }, [data]);

  return (
    <div
      style={{
        justifyContent: "center",
        textAlign: "center",
        minHeight: "100vh",
        margin: "2rem 1rem",
        width: "100",
      }}
    >
      <img src={image} style={{ width: "15rem", height: "auto" }} />
      <div
        style={{
          display: "grid",
          justifyContent: "start",
        }}
      >
        <h1 style={{ color: "#fff", fontWeight: "bold", marginBottom: "0" }}>
          {playlistName}
        </h1>
        <p>{username}</p>
      </div>
      <PlayCircleFilled
        style={{
          color: "#fff",
          display: "flex",
          justifyContent: "end",
          fontSize: "2rem",
        }}
      />
      {console.log(songs)}
      {songs != null &&
        songs.map((result, index) => (
          <div key={index} onClick={onClick}>
            <Row
              style={{
                paddingTop: "1rem",
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
              }}
            >
              <Col span={5}>
                <img src={result.albumImage} alt="" />
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
                    {result.title}
                  </h3>
                  <p style={{ color: "#fff", marginBottom: "0" }}>
                    {result.artist_list[0].name}
                  </p>
                </div>
              </Col>
              <Col span={2}>
                <button
                  type="button"
                  style={{
                    cursor: "pointer",
                    backgroundColor: "#626262",
                    padding: "0",
                    border: "none",
                    marginRight: "0",
                  }}
                >
                  <MoreOutlined
                    style={{
                      cursor: "pointer",
                      fontSize: "16px",
                      color: "#fff",
                      margin: "0",
                    }}
                  />
                </button>
              </Col>
            </Row>
          </div>
        ))}
    </div>
  );
};

export default Playlist;
