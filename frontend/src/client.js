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

export const addPlaylistToUser = (userId, playlistId) =>
  fetch(`/api/playlist/${playlistId}/user/${userId}`, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "PUT",
    body: JSON.stringify(userId),
  }).then(checkStatus);

export const addAlbum = (album) =>
  fetch("/api/album", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(album),
  });

export const addPlaylist = (playlist) =>
  fetch("/api/playlist", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(playlist),
  });

export const addSongToPlaylist = (playlistId, songId) =>
  fetch(`/api/playlist/${playlistId}/songAdd/${songId}`, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "PUT",
    body: JSON.stringify(songId),
  }).then(checkStatus);

export const getUserPlaylists = (userId) =>
  fetch(`/api/user/${userId}/playlists`).then(checkStatus);

export const getUser = (userId) =>
  fetch(`/api/user/${userId}`).then(checkStatus);

export const getPlaylist = (playlistId) =>
  fetch(`/api/playlist/${playlistId}/songs`).then(checkStatus);
