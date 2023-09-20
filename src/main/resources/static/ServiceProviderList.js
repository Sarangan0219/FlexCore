import React, { useState, useEffect } from 'react';
import { getServiceProviders } from './ServiceProviderAPI';

const ServiceProviderList = () => {
    const [serviceProviders, setServiceProviders] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchServiceProviders = async () => {
            try {
                const data = await getServiceProviders();
                setServiceProviders(data);
            } catch (err) {
                setError(err);
            }
        };

        fetchServiceProviders();
    }, []);

    if (error) {
        return <div>Error loading service providers</div>;
    }

    return (
        <div>
            <h1>Service Providers</h1>
            <ul>
                {serviceProviders.map((provider) => (
                    <li key={provider.id}>{provider.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default ServiceProviderList;
