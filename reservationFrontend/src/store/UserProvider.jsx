import React, { createContext, useState, useEffect, useContext } from 'react';

// Crear el contexto
export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    // Cargar el usuario del localStorage si existe
    const savedUser = localStorage.getItem('user');
    return savedUser ? JSON.parse(savedUser) : null;
  });

  const [restaurant, setRestaurant] = useState(() => {
    // Cargar el usuario del localStorage si existe
    const savedRestaurant = localStorage.getItem('restaurant');
    return savedRestaurant ? JSON.parse(savedRestaurant) : null;
  });

  // Guardar el usuario en localStorage cada vez que cambia
  useEffect(() => {
    if (user) {
      localStorage.setItem('user', JSON.stringify(user));
    } else {
      localStorage.removeItem('user'); // Eliminar el usuario si es null
    }
  }, [user]);

  useEffect(() => {
    if (restaurant) {
      localStorage.setItem('restaurant', JSON.stringify(restaurant));
    } else {
      localStorage.removeItem('restaurant'); // Eliminar el restaurante si es null
    }
  }, [restaurant]);

  return (
    <UserContext.Provider value={{ user, setUser, restaurant, setRestaurant }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUserContext = () => {
  return useContext(UserContext);
};

