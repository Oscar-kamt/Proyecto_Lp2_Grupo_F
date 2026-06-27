USE tienda_ropa;

CREATE VIEW v_header_boleta AS
    SELECT 
        v.id AS num_boleta,
        v.numero_boleta AS numBolText,
        u.usuario AS nombreCompletoUsuario,
        DATE_FORMAT(v.fecha, '%d/%m/%Y') AS fechaText
    FROM
        venta v
            INNER JOIN
        usuario u ON u.id = v.usuario_id;
        
        CREATE VIEW v_detail_boleta AS
SELECT

    dv.venta_id AS num_boleta,

    p.id AS id_producto,

    p.nombre AS descripcion,

    dv.cantidad,

    dv.precio AS precio_venta,

    dv.subtotal AS sub_total

FROM detalle_venta dv

INNER JOIN producto p
ON p.id = dv.producto_id;

