
Build:

    bundle exec jekyll build

Upload:

    rsync -avz _site/ nsmith@neelsmith.info:/var/www/html


Or all at once:

    bundle exec jekyll build && rsync -avz _site/ nsmith@neelsmith.info:/var/www/html
