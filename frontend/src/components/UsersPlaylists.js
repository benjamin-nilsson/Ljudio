import React, { useState, useEffect } from "react";
import "./../css/Playlist.css";
import Header from "./Header";
import Footer from "./Footer";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import { RightOutlined, PlusOutlined } from "@ant-design/icons";
import { Button, Col, Row } from "antd";
import AuthService from "../services/auth.service";
import EmployeeService from "../services/EmployeeService";
import { getUserPlaylists, getUser } from "./../client";

const Home = () => {
  const [content, setContent] = useState("");
  const [playlists, setPlaylists] = useState(null);
  const user = AuthService.getCurrentUser();
  const service = EmployeeService;

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

  useEffect(() => {
    service.getUserPlaylist(user.id).then((resp) => setPlaylists(resp.data));
  }, []);

  return (
    <div className="playlist-home">
      <Header />
      <div className="profile-image">
        <a href="/profile"><img
          src={
            "https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png"
          }

        /></a>
      </div>
      <div className="profile-user">
        <p>{user.username}</p>
      </div>
      <div className="my-playlists">
        <p>My Playlists</p>
        <Button
          className="home-button"
          type="create"
          href="/create"
          icon={<PlusOutlined style={{ fontSize: "190%" }} />}
        />
      </div>
      <div style={{ margin: "1rem 0" }}>
        {console.log(user.id)}
        {console.log(playlists)}
        {playlists != null &&
          playlists.map((result, index) => (
            <div key={index}>
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
                    <a href="/playlist">
                      <h3
                        style={{
                          color: "#fff",
                          marginBottom: "0",
                          fontWeight: "bold",
                        }}
                      >
                        {result.name}
                      </h3>
                    </a>
                    <p style={{ color: "#fff", marginBottom: "0" }}>{""}</p>
                  </div>
                </Col>
                <Col span={2}></Col>
              </Row>
            </div>
          ))}
      </div>
      <Footer />
    </div>
  );
};

export default Home;
