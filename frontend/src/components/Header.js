import React from "react";
import "./../css/Header.css";
import { Link, useLocation } from "react-router-dom";

const Header = () => {
  const { pathname } = useLocation();

  if (
    pathname === "/playback" ||
    pathname === "/playlist" ||
    pathname === "/find"
  )
    return null;
  return (
    <div>
      <Link to={"/"} className="header"></Link>
    </div>
  );
};

export default Header;
