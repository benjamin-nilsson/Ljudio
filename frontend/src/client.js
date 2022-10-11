import fetch from "unfetch";

const checkStatus = (response) => {
  if (response.ok) {
    return response;
  }

  const error = new Error(response.statusText);
  error.response = response;
  response.json().then((e) => {
    error.error = e;
  });
  return Promise.reject(error);
};

export const search = (title, artist) =>
  fetch(`/api/spotify/${title}/${artist}`).then(checkStatus);

export const track = (songAPIId) =>
  fetch(`/api/spotify/${songAPIId}`).then(checkStatus);

export const album = (albumAPIId) =>
  fetch(`/api/spotify/album/${albumAPIId}`).then(checkStatus);

export const addSong = (song) =>
  fetch("/api/song", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(song),
  });

export const addArtist = (artist) =>
  fetch("/api/artist", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(artist),
  });

export const createAPlaylist = (userId, playlistId) =>
  fetch(`/api/users/${userId}/playlist/${playlistId}/addPlaylistToUser`, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "PUT",
    body: JSON.stringify(userId),
  }).then(checkStatus);
