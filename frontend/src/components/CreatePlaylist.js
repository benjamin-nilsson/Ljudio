import React, { useState, useEffect, useRef } from "react";
import "./../css/CreatePlaylist.css";
import Header from "./Header";
import Footer from "./Footer";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import { useNavigate } from "react-router";
import { addPlaylistToUser, addPlaylist } from "../client";
import { Input, Col, Form, Row, Button } from "antd";
import { Link } from "react-router-dom";

const CreatePlaylist = () => {
  const [content, setContent] = useState("");
  const [playlist, setPlaylist] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [playlistName, setPlaylistName] = useState("");
  const isFirstRender = useRef(true);
  const [loading, setLoading] = useState(false);

  const newPlaylist = {
    name: "",
  };

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

  useEffect(() => {
    const fetchPlaylistToUser = () => {
      addPlaylistToUser(1, 1);
      console.log("hello");
    };
    fetchPlaylistToUser();
  }, [playlist]);

  const onFinish = () => {
    console.log(playlist);
    newPlaylist.name = playlistName;
    console.log(newPlaylist.name);
    let createdPlaylist = addPlaylist(newPlaylist);
    addPlaylistToUser(1, createdPlaylist.id);
    console.log(createdPlaylist.id);
    setPlaylist(createdPlaylist);
    navigate("/playlist-user");
    window.location.reload();
  };

  const renderForm = (
    <div>
      <form onSubmit={onFinish}>
        <div>
          <label className="create-label">Name your playlist</label>
          <input
            onChange={(e) => {
              setPlaylistName(e.target.value);
            }}
            type="text"
            name="name"
            required
          />
        </div>
        <input type="submit" value="Create playlist" />
      </form>
    </div>
  );

  return (
    <div className="create-playlist">
      <div>
        <div>{isSubmitted ? navigate("/playlist") : renderForm}</div>
      </div>
    </div>
  );
};

{
  /* <div className="create-playlist">
      <div>
        <div>Create Playlist</div>
        <div>
          <input type="text" onChange={e => setName(e.target.value) }/>
          <button onClick={onFinish}>add</button>
        </div>
      </div>
    </div> */
}

export default CreatePlaylist;
