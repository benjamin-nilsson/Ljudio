import React, { useState, useEffect } from "react";
import "./../css/Home.css";
import Header from "./Header";
import Footer from "./Footer";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import { RightOutlined, PlusOutlined } from "@ant-design/icons";
import { Button } from "antd";

const Home = () => {
  const [content, setContent] = useState("");

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
    <div className="home">
      <Header />
      <div className="profile-image">
        <img
          src={
            "https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png"
          }
        />
      </div>
      <div className="profile-user">
        <p>Username</p>
      </div>
      <div className="create-playlist">
        <p>My Playlists</p>
        <Button
          className="home-button"
          type="create"
          href="/create"
          icon={<PlusOutlined style={{ fontSize: "190%" }} />}
        />
      </div>
      <div className="playlist"></div>
      <div className="playlist-image">
        <img
          src={
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png"
          }
        />
        <Button
          className="home-button"
          type="create"
          href="/create"
          icon={<RightOutlined style={{ fontSize: "190%" }} />}
        />
      </div>
      <Footer />
    </div>
  );
};

export default Home;
