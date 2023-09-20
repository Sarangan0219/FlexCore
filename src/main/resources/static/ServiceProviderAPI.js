import axios from 'axios';

const API_URL = 'http://localhost:8080/api/v1/serviceProvider';

export const searchServiceProvider = async (name, perkType) => {
    try {
        const response = await axios.get(`${API_URL}/search`, {
            params: {
                name,
                perkType,
            },
        });
        return response.data;
    } catch (error) {
        console.error('Error searching service providers', error);
        throw error;
    }
};

export const getServiceProvider = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching service provider with id ${id}`, error);
        throw error;
    }
};

export const getServiceProviders = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching service providers', error);
        throw error;
    }
};

export const deleteServiceProvider = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error deleting service provider with id ${id}`, error);
        throw error;
    }
};

export const updateServiceProvider = async (serviceProvider) => {
    try {
        const response = await axios.put(API_URL, serviceProvider);
        return response.data;
    } catch (error) {
        console.error('Error updating service provider', error);
        throw error;
    }
};

export const registerServiceProvider = async (serviceProvider) => {
    try {
        const response = await axios.post(API_URL, serviceProvider);
        return response.data;
    } catch (error) {
        console.error('Error registering service provider', error);
        throw error;
    }
};

