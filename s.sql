select 
    cols.table_name, 
    cols.column_name, 
    cols.data_type, 
    fk.foreign_table_name, 
    fk.foreign_column_name, 
    coalesce(fk.is_primary, 'false') as is_primary, 
    coalesce(fk.is_foreign, 'false') as is_foreign 
from 
    information_schema.columns as cols 
left join 
    (
        SELECT 
            tc.table_name, 
            kcu.column_name, 
            ccu.table_name AS foreign_table_name, 
            ccu.column_name AS foreign_column_name, 
            case when tc.constraint_type='PRIMARY KEY' then 'true' else 'false' end as is_primary, 
            case when tc.constraint_type='FOREIGN KEY' then 'true' else 'false' end as is_foreign 
        FROM 
            information_schema.table_constraints AS tc 
        JOIN 
            information_schema.key_column_usage AS kcu 
        ON 
            tc.constraint_name = kcu.constraint_name 
                AND 
            tc.table_schema = kcu.table_schema 
        JOIN 
            information_schema.constraint_column_usage AS ccu 
        ON 
            ccu.constraint_name = tc.constraint_name 
        WHERE 
            tc.table_schema='public' 
                AND 
            tc.table_name='point_vente_users'
    ) 
    as 
        fk 
    on 
        cols.column_name=fk.column_name 
            and 
        cols.table_name=fk.table_name 
    where 
        cols.table_name='point_vente_users';

-- \d point_vente_users;