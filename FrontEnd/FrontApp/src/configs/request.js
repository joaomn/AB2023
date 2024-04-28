import axios from 'axios';

const baseURL = import.meta.env.API_URL || 'http://localhost:8080';

const axiosInstance = axios.create({
  baseURL,
});

export default axiosInstance;