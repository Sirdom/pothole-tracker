DROP TABLE IF EXISTS claims;

CREATE TABLE claims (
        claim_id SERIAL,
        pothole_id INT NOT NULL,
        description VARCHAR (800),
        name VARCHAR(100),
        phone_number VARCHAR(50),
        email VARCHAR(100),
        date_occurred DATE,
        vehicle_year INT,
        vehicle_model VARCHAR(100),
        vehicle_make VARCHAR(100),
        
        CONSTRAINT pk_claim_id PRIMARY KEY (claim_id),
        CONSTRAINT fk_pothole_id FOREIGN KEY (pothole_id) REFERENCES pothole_information (id)
        );