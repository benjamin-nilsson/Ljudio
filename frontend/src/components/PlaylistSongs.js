import React, { useEffect, useState } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import "../css/Playback.css";
import { Modal, Button, Col, Row } from "antd";
import { getPlaylist } from "./../client";
import {
  DownOutlined,
  MoreOutlined,
  PlayCircleFilled,
  StepBackwardFilled,
  StepForwardFilled,
} from "@ant-design/icons";
import AuthService from "../services/auth.service";

const PlaylistSong = ({ playlistName, playlistId }) => {
  const [content, setContent] = useState("");
  const [songs, setSongs] = useState(null);
  const user = AuthService.getCurrentUser();
  const [data, setData] = useState(false);
  const [loading, setLoading] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  const fetchPlaylist = () => {
    setLoading(true);
    getPlaylist(playlistId)
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
    fetchPlaylist();
    setData(false);
  }, [data]);

  useEffect(() => {
    UserService.getUserBoard().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setContent(_content);

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }, []);

  return (
    <div>
      {songs != null &&
        songs.map((result, index) => (
          <div key={index} onClick={showModal}>
            <Row
              style={{
                paddingTop: "1rem",
              }}
            >
              <Col span={5}>
                <img src={result.albumImage} alt="" />
              </Col>
              <Col span={17}>
                <div>
                  <p
                    style={{
                      color: "#fff",
                      marginBottom: "0",
                      fontWeight: "bold",
                      fontSize: "1rem",
                    }}
                  >
                    {result.title}
                  </p>
                  <p style={{ color: "#fff" }}>{result.artist_list[0].name}</p>
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
            <Modal
              footer={null}
              closable={false}
              onCancel={handleCancel}
              open={isModalOpen}
            >
              <div className="playback-all">
                <div className="playback-header">
                  <Row justify="space-between">
                    <Col span={4}>
                      <Button
                        className="header-button"
                        type="back"
                        href=""
                        icon=<DownOutlined
                          href="/playlist"
                          style={{ fontSize: "175%" }}
                        />
                      />
                    </Col>
                    <Col span={4}>
                      <div className="playlist-tag">playlist</div>
                      <div className="playlist-image">
                        <b>{playlistName}</b>
                      </div>
                    </Col>
                    <Col span={4}>
                      <Button
                        className="header-button"
                        onClick={handleCancel}
                        icon=<MoreOutlined style={{ fontSize: "175%" }} />
                      />
                    </Col>
                  </Row>
                  <img src={result.albumImage} />
                </div>
                <div className="artist-song">
                  <h2 style={{ color: "#fff", fontWeight: "bold" }}>
                    {result.title}
                  </h2>
                </div>
                <div className="artist-name">
                  <p style={{ color: "#fff" }}>{result.artist_list[0].name}</p>
                </div>
                <div className="playback-footer">
                  <div className="divider">
                    <Button
                      className="footer-button"
                      type="back"
                      href=""
                      icon={<StepBackwardFilled style={{ fontSize: "175%" }} />}
                    />
                    <Button
                      className="footer-button"
                      type="play"
                      href=""
                      icon={<PlayCircleFilled style={{ fontSize: "175%" }} />}
                    />
                    <Button
                      className="footer-button"
                      type="forward"
                      href=""
                      icon={<StepForwardFilled style={{ fontSize: "175%" }} />}
                    />
                  </div>
                </div>
              </div>
            </Modal>
          </div>
        ))}
    </div>
  );
};

export default PlaylistSong;
