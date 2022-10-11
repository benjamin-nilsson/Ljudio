import React, { useState } from "react";
import { Modal, Button } from "antd";
import {
  ArrowLeftOutlined,
  PlusSquareOutlined,
  MoreOutlined,
} from "@ant-design/icons";
import Playlists from "./Playlists";

const SelectedMediaItem = ({ id, src, title, artist }) => {
  const [isMainModel, setMainModel] = useState(false); // First Model
  const [isSubModel, setSubModel] = useState(false); // Second Model

  const onSubModel = (stateSub = true, stateMain = false) => {
    setMainModel(stateMain);
    setSubModel(stateSub);
  };

  return (
    <>
      <button
        type="button"
        style={{
          backgroundColor: "#626262",
          padding: "0",
          border: "none",
          marginRight: "0",
        }}
        onClick={() => setMainModel(true)}
      >
        <MoreOutlined
          style={{
            fontSize: "16px",
            color: "#fff",
            margin: "0",
          }}
        />
      </button>
      <Modal footer={null} closable={false} open={isMainModel}>
        <div style={{ display: "flex", flexDirection: "column" }}>
          <ArrowLeftOutlined
            onClick={() => setMainModel(false)}
            style={{
              textAlign: "start",
              fontSize: "1.5rem",
              color: "#A12424",
              marginBottom: "1rem",
            }}
          />
          <img
            src={src}
            style={{
              height: "auto",
              width: "100%",
            }}
          />
          <h3
            style={{
              color: "#fff",
              textAlign: "center",
              marginTop: "0.5rem",
              marginBottom: "0",
              fontWeight: "bold",
            }}
          >
            {title}
          </h3>
          <p style={{ textAlign: "center" }}>{artist}</p>
          <button
            onClick={onSubModel}
            type="button"
            style={{
              display: "flex",
              flexDirection: "row",
              alignItems: "center",
              background: "#626262",
              border: "none",
            }}
          >
            <PlusSquareOutlined
              style={{
                textAlign: "start",
                fontSize: "1.5rem",
                color: "#A12424",
                marginBottom: "1rem",
                marginRight: "0.5rem",
              }}
            />
            <p>Add to playlist</p>
          </button>
        </div>
      </Modal>
      <Modal footer={null} closable={false} open={isSubModel}>
        <div
          style={{
            display: "grid",
            gridAutoFlow: "column",
          }}
        >
          <ArrowLeftOutlined
            onClick={() => setSubModel(false)}
            style={{
              textAlign: "start",
              fontSize: "1.5rem",
              color: "#A12424",
              marginBottom: "1rem",
            }}
          />
          <h2 style={{ color: "#fff" }}>Spelistor</h2>
        </div>
        <Playlists id={id} />
        <Button
          style={{
            background: "#A12424",
            width: "8rem",
            color: "#fff",
            border: "none",
          }}
          block={false}
          shape="round"
          size="middle"
        >
          + New Playlist
        </Button>
      </Modal>
    </>
  );
};

export default SelectedMediaItem;
