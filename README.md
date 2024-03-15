
This plugin allows you server to restart monitoring of your server on [pixlbay.io](https://www.pixlbay.io) services. It's really useful for server hosting provider who automatically shutdown your server.


## Requirements
- have a server registered on [pixlbay.io](https://www.pixlbay.io)
- have an API key created in your server dashboard (`developper`->`API`)

## Configuration
- install the plugin in the `plugins` directory of your server
- start the server once to generate the configuration file (in `pixlbay` directory)
- edit the `config.yml` file and set the `API_KEY` to the one you created in your server dashboard. **The API key must start with `PXB_S_`**
- restart the server or use the `/reload` command to apply the configuration.

## Support
If you have any issue with the plugin, please open a ticket on the [Pixlbay Discord server](https://discord.gg/PB732uMvnN)

## Contributing
If you want to contribute to the plugin, please open a pull request on the github repository!

## License
This plugin is licensed under the MIT License. You can find the full license in the `LICENSE` file. 

