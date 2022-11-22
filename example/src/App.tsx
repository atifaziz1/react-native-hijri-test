import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
// import { multiply } from 'react-native-hijri-test';

// export default function App() {
//   const [result, setResult] = React.useState<number | undefined>();

//   // React.useEffect(() => {
//   //   multiply(3, 7).then(setResult);
//   // }, []);

//   return (
//     <View style={styles.container}>
//       <Text>Result: 23</Text>
//     </View>
//   );
// }

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     alignItems: 'center',
//     justifyContent: 'center',
//   },
//   box: {
//     width: 60,
//     height: 60,
//     marginVertical: 20,
//   },
// });

import { NativeModules } from 'react-native';

const { RNHijriDatePickerAndroid } = NativeModules;
console.log('\n\n\n\n**************insideAPP2*****************\n\n\n\n\n');
console.log(RNHijriDatePickerAndroid);
console.log('\n\n\n\n***************insideAPP2****************\n\n\n\n\n');
export default RNHijriDatePickerAndroid;
