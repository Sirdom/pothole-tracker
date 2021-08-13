DROP TABLE IF EXISTS schedule;

CREATE TABLE schedule (
        schedule_id SERIAL,
        pothole_id INT NOT NULL,
        status VARCHAR(256) DEFAULT 'Reported',
        date_reported DATE,
        date_inspected DATE,
        date_repaired DATE,
        severity INT DEFAULT 0,
        
        CONSTRAINT pk_schedule_id PRIMARY KEY (schedule_id),
        CONSTRAINT fk_pothole_id FOREIGN KEY (pothole_id) REFERENCES pothole_information (id)
        );
        
INSERT INTO schedule (pothole_id, date_reported, severity) VALUES (1, CURRENT_DATE, 3);
INSERT INTO schedule (pothole_id, date_reported, severity) VALUES (2, CURRENT_DATE, 3);
INSERT INTO schedule (pothole_id, date_reported, severity) VALUES (3, CURRENT_DATE, 3);
INSERT INTO schedule (pothole_id, date_reported, severity) VALUES (4, CURRENT_DATE, 3);
        

