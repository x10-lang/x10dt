#ifndef __X10_ARRAY_ROW_H
#define __X10_ARRAY_ROW_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_FUN_0_1_H_NODEPS
#include <x10/lang/Fun_0_1.h>
#undef X10_LANG_FUN_0_1_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 
class Printer;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace io { 
class StringWriter;
} } 
namespace x10 { namespace array { 

class Row : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(cols);
    
    void _instance_init();
    
    virtual x10_int __apply(x10_int i) = 0;
    virtual x10_int __set(x10_int v, x10_int i) = 0;
    void _constructor(x10_int cols);
    
    virtual void printInfo(x10aux::ref<x10::io::Printer> ps, x10_int row);
    virtual void printEqn(x10aux::ref<x10::io::Printer> ps, x10aux::ref<x10::lang::String> spc,
                          x10_int row);
    virtual x10aux::ref<x10::lang::String> toString();
    x10_int cols();
    virtual x10aux::ref<x10::array::Row> x10__array__Row____x10__array__Row__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_ROW_H

namespace x10 { namespace array { 
class Row;
} } 

#ifndef X10_ARRAY_ROW_H_NODEPS
#define X10_ARRAY_ROW_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Int.h>
#include <x10/io/Printer.h>
#include <x10/lang/String.h>
#include <x10/lang/Boolean.h>
#include <x10/io/StringWriter.h>
#ifndef X10_ARRAY_ROW_H_GENERICS
#define X10_ARRAY_ROW_H_GENERICS
#endif // X10_ARRAY_ROW_H_GENERICS
#endif // __X10_ARRAY_ROW_H_NODEPS
