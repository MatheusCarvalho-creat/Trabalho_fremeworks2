import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import MoviesList from './pages/MoviesList';
import MovieCreate from './pages/MovieCreate';
import MovieDetails from './pages/MovieDetails';
import { Container, AppBar, Toolbar, Button } from '@mui/material';
import api from './services/api';

function App(){
  const logout = () => {
    localStorage.removeItem('token');
    window.location.href = '/login';
  };
  return (
    <BrowserRouter>
      <AppBar position="static">
        <Toolbar>
          <Button color="inherit" href="/movies">Filmes</Button>
          <Button color="inherit" href="/movies/new">Criar</Button>
          <Button color="inherit" href="/login" onClick={logout}>Sair</Button>
        </Toolbar>
      </AppBar>
      <Container sx={{mt:4}}>
        <Routes>
          <Route path="/" element={<Navigate to="/movies" replace />} />
          <Route path="/login" element={<Login/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/movies" element={<MoviesList/>} />
          <Route path="/movies/new" element={<MovieCreate/>} />
          <Route path="/movies/:id" element={<MovieDetails/>} />
        </Routes>
      </Container>
    </BrowserRouter>
  );
}
export default App;
