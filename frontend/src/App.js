import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Link,
  Route,
  Navigate,
} from "react-router-dom";
import { UserContext } from "./common/UserContext";
import "./App.css";
import "./css/loginRegister.css";
import "./css/Header.css";

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import Register from "./components/register.component";
import Profile from "./components/profile.component";
import BoardAdmin from "./components/board-admin.component";
import AddEmployeeComponent from "./components/AddEmployeeComponent";
import Find from "./components/search/Find";
import Footer from "./components/Footer";

import EventBus from "./common/EventBus";
import Header from "./components/Header";
import CreatePlaylist from "./components/CreatePlaylist";
import Playlist from "./components/Playlist";

const App = () => {
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, []);

  const logOut = () => {
    AuthService.logout();
    setShowAdminBoard(false);
    setCurrentUser(undefined);
  };

  return (
    <div>
      <div className="container mt-3">
        <Header />
        {currentUser && <Footer />}
        <UserContext.Provider value={{ currentUser, setCurrentUser }}>
          <Routes>
            <Route path="" element={<Navigate to="/login" replace />} />
            {currentUser && (
              <Route
                path="/login"
                element={<Navigate to="/playlist-user" replace />}
              />
            )}
            {currentUser && (
              <Route
                path="/register"
                element={<Navigate to="/playlist-user" replace />}
              />
            )}
            <Route path="/playlist" element={<Playlist />} />
            <Route path="/login" element={<Login />} />
            <Route path="/find" element={<Find />} />
            <Route path="/register" element={<Register />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/admin" element={<BoardAdmin />} />
            <Route path="/create" element={<CreatePlaylist />} />
            <Route
              path="/edit-employee/:id"
              element={<AddEmployeeComponent />}
            />
          </Routes>
        </UserContext.Provider>
      </div>
    </div>
  );
};

export default App;
