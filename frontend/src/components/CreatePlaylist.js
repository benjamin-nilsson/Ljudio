import React, { useState, useEffect } from "react";
import "./../css/CreatePlaylist.css";
import Header from "./Header";
import Footer from "./Footer";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import { useNavigate } from "react-router";
import { createAPlaylist, addPlaylist } from "./../client";
import { Input, Col, Form, Row, Button } from "antd";

const CreatePlaylist = () => {
  const [content, setContent] = useState("");
  const [playlist, setPlaylist] = useState({});

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

  const navigate = useNavigate();

  const addPlaylistToUser = () => {
    createAPlaylist(1, playlist.id);
  };

  const onFinish = (playlist) => {
    console.log(playlist);
    let createdPlaylist = addPlaylist(playlist);
    setPlaylist(createdPlaylist);
    console.log(playlist);
    addPlaylistToUser();
    navigate("/playlist");
  };


  const onFinishFailed = (errorInfo) => {
    alert(JSON.stringify(errorInfo, null, 2));
  };

  return (
    <div className="create-playlist">
      <Header />
      <div className="create">
        <p>Name your playlist</p>
        <Form
          layout="vertical"
          onFinishFailed={onFinishFailed}
          onFinish={onFinish}
          hideRequiredMark
        >
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                  name="name"
                  label="name"
                rules={[{ required: true, message: "Name your playlist." }]}
              >
                <Input />
                <Form.Item>
                  <input type="submit" value="Create Playlist" />
                  <div className="button-playlist"></div>
                </Form.Item>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </div>
      <Footer />
    </div>
  );
};

export default CreatePlaylist;
