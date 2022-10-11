import React, { useState } from "react";
import { Col, Row } from "antd";
import "../../css/Card.css";
import SelectedMediaItem from "./SelectedMediaItem";

const Card = ({ id, src, title, artist }) => {
  return (
    <div>
      <Row
        style={{
          paddingTop: "1rem",
          display: "flex",
          flexDirection: "row",
          alignItems: "center",
        }}
      >
        <Col span={5}>
          <img src={src} alt="" />
        </Col>
        <Col span={17}>
          <div>
            <h3
              style={{ color: "#fff", marginBottom: "0", fontWeight: "bold" }}
            >
              {title}
            </h3>
            <p style={{ color: "#fff", marginBottom: "0" }}>{artist}</p>
          </div>
        </Col>
        <Col span={2}>
          <SelectedMediaItem id={id} src={src} title={title} artist={artist} />
        </Col>
      </Row>
    </div>
  );
};

export default Card;
