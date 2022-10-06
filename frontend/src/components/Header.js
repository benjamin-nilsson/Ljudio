import React, { useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import "./../css/Header.css";

const Header = () => {
  return (
    <div className="header">
      <Link className="link" to="/"></Link>
      <img src="./images/Ljudio_white_png" alt="Ljudio"></img>
    </div>
  );
};

export default Header;
