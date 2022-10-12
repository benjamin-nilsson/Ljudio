import React from "react";
import "./../css/Footer.css";
import {
  HomeOutlined,
  MenuUnfoldOutlined,
  SearchOutlined,
} from "@ant-design/icons";
import { Button } from "antd";
import {useLocation} from "react-router-dom";

const Footer = () => {
  const { pathname } = useLocation();

  if (pathname === "/playback") return null;

  return (
    <div className="footer">
      <div className="divider">
        <Button
          className="footer-button"
          type="home"
          href="/"
          icon={<HomeOutlined style={{ fontSize: "175%" }} />}
        />
        <Button
          className="footer-button"
          type="search"
          href="/search"
          icon={<SearchOutlined style={{ fontSize: "175%" }} />}
        />
        <Button
          className="footer-button"
          type="playlist"
          href="/playlist"
          icon={<MenuUnfoldOutlined style={{ fontSize: "175%" }} />}
        />
      </div>
    </div>
  );
};
export default Footer;
