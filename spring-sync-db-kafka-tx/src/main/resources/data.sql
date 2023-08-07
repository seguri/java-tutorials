INSERT INTO users (id, name, email, version)
VALUES ('04A1EDB8-7612-40FA-93F7-15E6F48A8419', 'John Doe', 'john.doe@example.com', 0)
ON CONFLICT (id) DO UPDATE SET name    = 'John Doe',
                               email   = 'john.doe@example.com',
                               version = 0;

CREATE OR REPLACE FUNCTION generate_exception(error_message text)
    RETURNS VOID AS '
    BEGIN
        RAISE EXCEPTION ''Custom Exception: %'', error_message;
    END;
' LANGUAGE plpgsql;
