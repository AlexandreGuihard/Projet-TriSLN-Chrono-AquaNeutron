import os
import sys
import mysql.connector
from reportlab.lib.pagesizes import letter, landscape
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle, Paragraph
from reportlab.lib import colors
from reportlab.lib.styles import getSampleStyleSheet
import subprocess

def generate_pdf_classement(host, user, password, database, genre, categorie):
    formatted_genre = genre.lower().replace(" ", "_")
    formatted_categorie = categorie.lower().replace(" ", "_")
    output_file = f"pdf/Classement_{formatted_genre}_{formatted_categorie}.pdf"

    if not os.path.exists('pdf'):
        os.makedirs('pdf')

    try:
        conn = mysql.connector.connect(
            host=host,
            user=user,
            password=password,
            database=database
        )
        cursor = conn.cursor()

        genre_condition = ""
        categorie_condition = ""
        
        if genre.lower() != "mixte":
            if genre.lower() == "homme":
                genre = "M"
            else:
                genre = "F"
            genre_condition = f"AND P.sexe = '{genre}'"
        
        if categorie.lower() != "toutes":
            categorie_condition = f"AND Cat.categorie = '{categorie}'"

        query = f"""
        SELECT 
            C.pos_generale AS Positions,
            C.temps AS Temps,
            CONCAT(P.nom, ' ', P.prenom) AS Nom_Prénom,
            P.club AS Club_Equipe,
            D.num_dossard AS Dossard,
            Cat.categorie AS Catégorie,
            C.pos_categorie AS Classements_Catégorie,
            P.num_Licence AS Licence
        FROM CLASSEMENT C
        JOIN GENERER G ON C.id_Classement = G.id_Classement
        JOIN PARTICIPANT P ON G.id_Participant = P.id_Participant
        JOIN DOSSARD D ON P.id_Participant = D.id_Participant
        JOIN CATEGORIE Cat ON P.idCategorie = Cat.idCategorie
        WHERE 1=1 {genre_condition} {categorie_condition};
        """
        
        cursor.execute(query)
        rows = cursor.fetchall()

        if not rows:
            print("Aucun résultat trouvé dans la base de données.")
        else:
            print(f"{len(rows)} résultats trouvés.")
        
        column_names = [desc[0] for desc in cursor.description]

        pdf = SimpleDocTemplate(output_file, pagesize=landscape(letter))
        elements = []

        styles = getSampleStyleSheet()
        title_style = styles['Heading1']

        title_paragraph = Paragraph(f"Classement - {genre.capitalize()} - {categorie.capitalize()}", title_style)
        elements.append(title_paragraph)

        data = [column_names] + rows

        num_columns = len(column_names)
        table_width = 720
        column_widths = [table_width / num_columns] * num_columns

        table = Table(data, colWidths=column_widths)

        style = TableStyle([
            ("BACKGROUND", (0, 0), (-1, 0), colors.grey),
            ("TEXTCOLOR", (0, 0), (-1, 0), colors.whitesmoke),
            ("ALIGN", (0, 0), (-1, -1), "CENTER"),
            ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
            ("FONTSIZE", (0, 0), (-1, -1), 8),
            ("BOTTOMPADDING", (0, 0), (-1, 0), 10),
            ("GRID", (0, 0), (-1, -1), 1, colors.black),
        ])
        table.setStyle(style)

        elements.append(table)
        
        try:
            pdf.build(elements)
        except Exception as e:
            print(e)

        subprocess.run(['xdg-open', output_file])

    except mysql.connector.Error as err:
        print(f"Erreur de connexion ou d'exécution SQL : {err}")
    except Exception as e:
        print(f"Erreur lors de la génération du PDF : {e}")
    finally:
        if conn.is_connected():
            cursor.close()
            conn.close()
            print("Connexion à la base de données fermée.")

if __name__ == "__main__":
    if len(sys.argv) != 7:
        print("Usage : python3 generationsPDF.py <host> <user> <password> <database> <genre> <categorie>")
        sys.exit(1)

    host = sys.argv[1]
    user = sys.argv[2]
    password = sys.argv[3]
    database = sys.argv[4]
    genre = sys.argv[5]
    categorie = sys.argv[6]

    generate_pdf_classement(host, user, password, database, genre, categorie)
