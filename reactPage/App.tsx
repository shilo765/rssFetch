import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Linking,Image } from 'react-native';

const App = () => {
  const [currentTime, setCurrentTime] = useState(new Date());
  const OpenURLButton = ({ url, children }) => {
    const handlePress = async () => {
      // Check if the link is supported
      const supported = await Linking.canOpenURL(url);
  
      
        // Opening the link in the browser
      await Linking.openURL(url);
      
    };
  
    return (
      <TouchableOpacity onPress={handlePress}>
        <Text style={styles.link}>{children}</Text>
      </TouchableOpacity>
    );
  };
  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000*60);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <View style={styles.container}>
      <Text style={{ fontSize: 20,color:"black",lineHeight: 300}}>Shilo Ben natan</Text>
      <Text style={styles.text}>Current Time:</Text>
      <Text style={styles.text}>{`${currentTime.toLocaleDateString()} ${currentTime.toLocaleTimeString().slice(0, 5)}`}</Text>
      <OpenURLButton url="https://www.cnn.com/travel/article/virgin-orbit-boeing-747-newquay-scn/index.html">Virgin Boeing 747 to launch rocket into spac</OpenURLButton>
      <Image source={{ uri: 'https://media.cnn.com/api/v1/images/stellar/prod/221019093530-05b-uk-spaceport-747.jpg?q=w_1110,c_fill/f_webp' }} style={{ width: 200, height: 200 }} />



    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    
    alignItems: 'center',
    backgroundColor: '#F1EED7',
   
  },
  text: {
    fontSize: 20,
    textAlign: 'center',
    color:"#F42218",
    lineHeight: 50
  },
});

export default App;
