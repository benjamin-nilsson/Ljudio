import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";

const BoardUser = () => {
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
          <h3 style={{
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    marginTop: "100px"
}}/>
  );
};

export default BoardUser;