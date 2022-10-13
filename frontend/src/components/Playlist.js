import React from "react";
import PlaylistSongs from "./PlaylistSongs";

import { PlayCircleFilled } from "@ant-design/icons";

const Playlist = ({ playlistName, playlistId, username, image }) => {
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
      <PlaylistSongs playlistId={playlistId} />
    </div>
  );
};

export default Playlist;
