import requests
from lxml import html
import string

BASE_URL = "http://www.icy-veins.com/hearthstone/arena-warlock-card-rankings-spreadsheet"

def create_json():
	cards = open("arenacards.json", "w")
	tojson = "{\"cardnames\": ["
	page = requests.get(BASE_URL)
	tree = html.fromstring(page.text)
	commontable = tree.xpath('//*[@id="arena_spreadsheet_table_common"]')
	count = 0
	for item in commontable:
		tojson = tojson + item
	cards.write(tojson)

create_json()